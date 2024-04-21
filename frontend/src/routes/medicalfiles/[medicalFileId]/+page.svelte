<script lang="ts">
    import type { MedicalVisit } from "$lib/types/medicalVisit";
    import type { MedicalHistory } from "$lib/types/medicalHistory";
    import type { PageServerData } from "./$types";
    import DataTable, {
        Head,
        Body,
        Row,
        Cell,
        SortValue,
        Pagination,
    } from "@smui/data-table";
    import IconButton from "@smui/icon-button";
    import Paper, { Title, Subtitle, Content } from "@smui/paper";
    import Select, { Option } from "@smui/select";
    import Button, { Label } from "@smui/button";
    import { API_URL } from "../../../constants";
    import Textfield from "@smui/textfield";
    // import Icon from '@smui/textfield/icon';
    import HelperText from "@smui/textfield/helper-text";

    export let data: PageServerData;

    $: ({ medicalFile } = data);

    $: medicalVisitList = medicalFile.medicalVisitList;

    let rowsPerPage = 10;
    let currentPage = 0;

    $: start = currentPage * rowsPerPage;
    $: end = Math.min(start + rowsPerPage, medicalVisitList.length);
    $: slice = medicalVisitList.slice(start, end);
    $: lastPage = Math.max(
        Math.ceil(medicalVisitList.length / rowsPerPage) - 1,
        0,
    );

    $: if (currentPage > lastPage) {
        currentPage = lastPage;
    }

    $: if (medicalVisitList.length > rowsPerPage) {
        currentPage = 1;
    } else {
        currentPage = 0;
    }

    let sort: keyof MedicalVisit = "id";
    let sortDirection: Lowercase<keyof typeof SortValue> = "ascending";

    function handleSort() {
        medicalVisitList.sort((a: MedicalVisit, b: MedicalVisit) => {
            let aVal: any, bVal: any;
            if (sort === "establishment") {
                aVal = a.establishment;
                bVal = b.establishment;
            } else if (sort === "doctor") {
                aVal = a.doctor;
                bVal = b.doctor;
            } else if (sort === "dateOfVisit") {
                aVal = a.dateOfVisit;
                bVal = b.dateOfVisit;
            } else if (sort === "diagnostic") {
                aVal = a.diagnostic;
                bVal = b.diagnostic;
            } else if (sort === "treatment") {
                aVal = a.treatment;
                bVal = b.treatment;
            }

            const [sortedAVal, sortedBVal] = [aVal, bVal][
                sortDirection === "ascending" ? "slice" : "reverse"
            ]();
            if (
                typeof sortedAVal === "string" &&
                typeof sortedBVal === "string"
            ) {
                return sortedAVal.localeCompare(sortedBVal);
            }
            return Number(sortedAVal) - Number(sortedBVal);
        });
        medicalVisitList = medicalVisitList;
    }

    let showModal = false;

    function toggleModal() {
        showModal = !showModal;
    }

    let establishment = "";
    let doctor = "";
    let dateOfVisit = "";
    let diagnostic = "";
    let treatment = "";
    let summary = "";
    let notes = "";

    async function deleteVisit(visitId: number) {
        const id = medicalFile.id;
        const response = await fetch(
            `http://localhost:8080/medical-files/${id}/medical-visits`,
            {
                method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(visitId),
            },
        );
        const updatedMedicalFileResponse = await fetch(
            `${API_URL}/medical-files/${id}`,
        );
        if (!updatedMedicalFileResponse.ok) {
            console.error(
                "Failed to fetch updated MedicalFile",
                await updatedMedicalFileResponse.text(),
            );
            return;
        }

        // Update the medicalFile variable
        medicalFile = await updatedMedicalFileResponse.json();
    }

    async function submitForm() {
        const id = medicalFile.id;
        const medicalVisit = {
            establishment,
            doctor,
            dateOfVisit,
            diagnostic,
            treatment,
            summary,
            notes,
        };
        const response = await fetch(
            `${API_URL}/medical-files/${id}/medical-visits`,
            {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(medicalVisit),
            },
        );
        if (!response.ok) {
            console.error(
                "Failed to create medical visit",
                await response.text(),
            );
            return;
        }
        // Fetch the updated MedicalFile
        const updatedMedicalFileResponse = await fetch(
            `${API_URL}/medical-files/${id}`,
        );
        if (!updatedMedicalFileResponse.ok) {
            console.error(
                "Failed to fetch updated MedicalFile",
                await updatedMedicalFileResponse.text(),
            );
            return;
        }

        // Update the medicalFile variable
        medicalFile = await updatedMedicalFileResponse.json();
    }

    $: medicalHistoryList = medicalFile.medicalHistoryList;

    let rowsPerPageHistory = 10;
    let currentPageHistory = 0;

    $: startHistory = currentPageHistory * rowsPerPageHistory;
    $: endHistory = Math.min(
        startHistory + rowsPerPageHistory,
        medicalHistoryList.length,
    );
    $: sliceHistory = medicalHistoryList.slice(startHistory, endHistory);
    $: lastPageHistory = Math.max(
        Math.ceil(medicalHistoryList.length / rowsPerPageHistory) - 1,
        0,
    );

    $: if (currentPageHistory > lastPageHistory) {
        currentPageHistory = lastPageHistory;
    }

    $: if (medicalHistoryList.length > rowsPerPageHistory) {
        currentPageHistory = 1;
    } else {
        currentPageHistory = 0;
    }

    let sortHistory: keyof MedicalHistory = "id";
    let sortDirectionHistory: Lowercase<keyof typeof SortValue> = "ascending";

    function handleSortHistory() {
        medicalHistoryList.sort((a: MedicalHistory, b: MedicalHistory) => {
            let aVal: any, bVal: any;
            if (sortHistory === "diagnostic") {
                aVal = a.diagnostic;
                bVal = b.diagnostic;
            } else if (sortHistory === "treatment") {
                aVal = a.treatment;
                bVal = b.treatment;
            } else if (sortHistory === "doctor") {
                aVal = a.doctor;
                bVal = b.doctor;
            }

            const [sortedAVal, sortedBVal] = [aVal, bVal][
                sortDirectionHistory === "ascending" ? "slice" : "reverse"
            ]();
            if (
                typeof sortedAVal === "string" &&
                typeof sortedBVal === "string"
            ) {
                return sortedAVal.localeCompare(sortedBVal);
            }
            return Number(sortedAVal) - Number(sortedBVal);
        });
        medicalHistoryList = medicalHistoryList;
    }

    let showHistoryModal = false;

    function toggleHistoryModal() {
        showHistoryModal = !showHistoryModal;
    }

    let diagnosticHistory = "";
    let treatmentHistory = "";
    let doctorHistory = "";
    let startDateHistory = "";
    let endDateHistory = "";

    async function submitHistoryForm() {
        const id = medicalFile.id;
        const medicalHistory = {
            diagnostic: diagnosticHistory,
            treatment: treatmentHistory,
            doctor: doctorHistory,
            startDate: startDateHistory,
            endDate: endDateHistory,
        };
        const response = await fetch(
            `${API_URL}/medical-files/${id}/medical-histories`,
            {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(medicalHistory),
            },
        );
        if (!response.ok) {
            console.error(
                "Failed to create medical history",
                await response.text(),
            );
            return;
        }
        // Fetch the updated MedicalFile
        const updatedMedicalFileResponse = await fetch(
            `${API_URL}/medical-files/${id}`,
        );
        if (!updatedMedicalFileResponse.ok) {
            console.error(
                "Failed to fetch updated MedicalFile",
                await updatedMedicalFileResponse.text(),
            );
            return;
        }

        // Update the medicalFile variable
        medicalFile = await updatedMedicalFileResponse.json();
    }

    async function deleteHistory(historyId: number) {
        const id = medicalFile.id;
        const response = await fetch(
            `http://localhost:8080/medical-files/${id}/medical-histories`,
            {
                method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(historyId),
            },
        );
        const updatedMedicalFileResponse = await fetch(
            `${API_URL}/medical-files/${id}`,
        );
        if (!updatedMedicalFileResponse.ok) {
            console.error(
                "Failed to fetch updated MedicalFile",
                await updatedMedicalFileResponse.text(),
            );
            return;
        }

        // Update the medicalFile variable
        medicalFile = await updatedMedicalFileResponse.json();
    }
</script>

<svelte:head>
    <title>Medical File {medicalFile.insuranceNumber}</title>
</svelte:head>

<div class="paper-container">
    <Paper>
        <Title>Medical File {medicalFile.insuranceNumber}</Title>
        <Subtitle>Patient</Subtitle>
        <Content
            >{medicalFile.patient?.firstName}
            {medicalFile.patient?.lastName}</Content
        >
        <Button href="/medicalfiles">Back to Medical Files</Button>
    </Paper>
    <Paper>
        <Title>Medical Visit List</Title>
        <Content>
            {#if showModal}
                <Button on:click={toggleModal}>Close</Button>
            {:else}
                <Button on:click={toggleModal}>Add new medical visit</Button>
            {/if}
        </Content>
    </Paper>

    {#if showModal}
        <div class="modal">
            <div class="container">
                <form on:submit|preventDefault={submitForm}>
                    <Textfield
                        variant="outlined"
                        bind:value={establishment}
                        label="Establishment"
                    >
                        <HelperText slot="helper"
                            >Hospital / clinic name</HelperText
                        >
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={doctor}
                        label="Doctor"
                    >
                        <HelperText slot="helper">Doctor's name</HelperText>
                    </Textfield>
                    <Textfield
                        type="date"
                        variant="outlined"
                        bind:value={dateOfVisit}
                        label="Date of visit"
                    >
                        <HelperText slot="helper">Date of visit</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={diagnostic}
                        label="Diagnostic"
                    >
                        <HelperText slot="helper">Diagnostic</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={treatment}
                        label="Treatement"
                    >
                        <HelperText slot="helper">Treatement</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={summary}
                        label="Summary"
                    >
                        <HelperText slot="helper">Summary</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={notes}
                        label="Notes"
                    >
                        <HelperText slot="helper">Notes</HelperText>
                    </Textfield>
                    <Button type="submit" variant="raised"
                        >Add Medical Visit</Button
                    >
                </form>
            </div>
        </div>
    {/if}

    <DataTable
        sortable
        bind:sort
        bind:sortDirection
        on:SMUIDataTable:sorted={handleSort}
        table$aria-label="Medical Visit list"
        style="width: 100%;"
    >
        <Head>
            <Row>
                <Cell columnId="establishment">
                    <Label>Establishment</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="doctor">
                    <Label>Doctor</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="dateOfVisit">
                    <Label>Date Of Visit</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="diagnostic">
                    <Label>Diagnostic</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="treatment">
                    <Label>Treatment</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
            </Row>
        </Head>
        <Body>
            {#each slice as medicalVisit (medicalVisit.id)}
                <Row>
                    <Cell>
                        <a
                            href={`/medicalfiles/${medicalFile.id}/visits/${medicalVisit.id}`}
                        >
                            {medicalVisit.establishment}
                        </a>
                    </Cell>
                    <Cell>{medicalVisit.doctor}</Cell>
                    <Cell>{new Date(medicalVisit.dateOfVisit).toISOString().split('T')[0]}</Cell>
                    <Cell>{medicalVisit.diagnostic}</Cell>
                    <Cell>{medicalVisit.treatment}</Cell>
                    <Cell>
                        <Button
                            on:click={() => {
                                deleteVisit(medicalVisit.id);
                            }}>Delete</Button
                        >
                    </Cell>
                </Row>
            {/each}
        </Body>
        <Pagination slot="paginate">
            <svelte:fragment slot="rowsPerPage">
                <Label>Rows Per Page</Label>
                <Select variant="outlined" bind:value={rowsPerPage} noLabel>
                    <Option value={10}>10</Option>
                    <Option value={25}>25</Option>
                    <Option value={100}>100</Option>
                </Select>
            </svelte:fragment>
            <svelte:fragment slot="total">
                {start + 1}-{end} of {medicalVisitList.length}
            </svelte:fragment>

            <IconButton
                class="material-icons"
                action="first-page"
                title="First page"
                on:click={() => (currentPage = 0)}
                disabled={currentPage === 0}>first_page</IconButton
            >
            <IconButton
                class="material-icons"
                action="prev-page"
                title="Prev page"
                on:click={() => currentPage--}
                disabled={currentPage === 0}>chevron_left</IconButton
            >
            <IconButton
                class="material-icons"
                action="next-page"
                title="Next page"
                on:click={() => currentPage++}
                disabled={currentPage === lastPage}>chevron_right</IconButton
            >
            <IconButton
                class="material-icons"
                action="last-page"
                title="Last page"
                on:click={() => (currentPage = lastPage)}
                disabled={currentPage === lastPage}>last_page</IconButton
            >
        </Pagination>
    </DataTable>

    <Paper>
        <Title>Medical History List</Title>
        <Content>
            {#if showHistoryModal}
                <Button on:click={toggleHistoryModal}>Close</Button>
            {:else}
                <Button on:click={toggleHistoryModal}
                    >Add new medical history</Button
                >
            {/if}
        </Content>
    </Paper>

    {#if showHistoryModal}
        <div class="modal">
            <div class="container">
                <form on:submit|preventDefault={submitHistoryForm}>
                    <Textfield
                        variant="outlined"
                        bind:value={diagnosticHistory}
                        label="Diagnostic"
                    >
                        <HelperText slot="helper">Diagnostic</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={treatmentHistory}
                        label="Treatement"
                    >
                        <HelperText slot="helper">Treatement</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={doctorHistory}
                        label="Doctor"
                    >
                        <HelperText slot="helper">Doctor's name</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={startDateHistory}
                        type="date"
                        label="Start Date"
                    >
                        <HelperText slot="helper">Start Date</HelperText>
                    </Textfield>
                    <Textfield
                        variant="outlined"
                        bind:value={endDateHistory}
                        type="date"
                        label="End Date"
                    >
                        <HelperText slot="helper">End Date</HelperText>
                    </Textfield>
                    <Button type="submit" variant="raised"
                        >Add Medical History</Button
                    >
                </form>
            </div>
        </div>
    {/if}

    <DataTable
        sortable
        bind:sort={sortHistory}
        bind:sortDirection={sortDirectionHistory}
        on:SMUIDataTable:sorted={handleSortHistory}
        table$aria-label="Medical History list"
        style="width: 100%;"
    >
        <Head>
            <Row>
                <Cell columnId="diagnostic">
                    <Label>Diagnostic</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="treatment">
                    <Label>Treatment</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="doctor">
                    <Label>Doctor</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="startDate">
                    <Label>Start Date</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
                <Cell columnId="endDate">
                    <Label>End Date</Label>
                    <IconButton class="material-icons">arrow_upward</IconButton>
                </Cell>
            </Row>
        </Head>
        <Body>
            {#each sliceHistory as medicalHistory (medicalHistory.id)}
                <Row>
                    <Cell>
                        <a
                            href={`/medicalfiles/${medicalFile.id}/histories/${medicalHistory.id}`}
                        >
                            {medicalHistory.diagnostic}
                        </a>
                    </Cell>
                    <Cell>{medicalHistory.treatment}</Cell>
                    <Cell>{medicalHistory.doctor}</Cell>
                    <Cell>{new Date(medicalHistory.startDate).toISOString().split('T')[0]}</Cell>
                    <!-- <Cell>{medicalHistory.startDate}</Cell> -->
                    <Cell>{new Date(medicalHistory.endDate).toISOString().split('T')[0]}</Cell>
                    <!-- <Cell>{medicalHistory.endDate}</Cell> -->
                    <Cell>
                        <Button
                            on:click={() => {
                                deleteHistory(medicalHistory.id);
                            }}>Delete</Button
                        >
                    </Cell>
                </Row>
            {/each}
        </Body>
        <Pagination slot="paginate">
            <svelte:fragment slot="rowsPerPage">
                <Label>Rows Per Page</Label>
                <Select
                    variant="outlined"
                    bind:value={rowsPerPageHistory}
                    noLabel
                >
                    <Option value={10}>10</Option>
                    <Option value={25}>25</Option>
                    <Option value={100}>100</Option>
                </Select>
            </svelte:fragment>
            <svelte:fragment slot="total">
                {startHistory + 1}-{endHistory} of {medicalHistoryList.length}
            </svelte:fragment>

            <IconButton
                class="material-icons"
                action="first-page"
                title="First page"
                on:click={() => (currentPageHistory = 0)}
                disabled={currentPageHistory === 0}>first_page</IconButton
            >
            <IconButton
                class="material-icons"
                action="prev-page"
                title="Prev page"
                on:click={() => currentPageHistory--}
                disabled={currentPageHistory === 0}>chevron_left</IconButton
            >
            <IconButton
                class="material-icons"
                action="next-page"
                title="Next page"
                on:click={() => currentPageHistory++}
                disabled={currentPageHistory === lastPageHistory}
                >chevron_right</IconButton
            >
            <IconButton
                class="material-icons"
                action="last-page"
                title="Last page"
                on:click={() => (currentPageHistory = lastPageHistory)}
                disabled={currentPageHistory === lastPageHistory}
                >last_page</IconButton
            >
        </Pagination>
    </DataTable>
</div>

<style>
    form {
        display: flex;
        flex-direction: column;
        width: 90%;
        margin: 0 auto;
    }
</style>

