<script lang="ts">
    import type { MedicalFile } from "$lib/types/medicalFile";
    import { onMount } from "svelte";
    import type { PageData } from "./$types";
    import DataTable, {
        Head,
        Body,
        Row,
        Cell,
        Label,
        SortValue,
        Pagination,
    } from "@smui/data-table";
    import IconButton from "@smui/icon-button";
    // import { Label } from '@smui/common';
    import Select, { Option } from "@smui/select";

    export let data: PageData;

    $: ({ medicalFiles } = data);

    let rowsPerPage = 10;
    let currentPage = 0;

    $: start = currentPage * rowsPerPage;
    $: end = Math.min(start + rowsPerPage, medicalFiles.length);
    $: slice = medicalFiles.slice(start, end);
    $: lastPage = Math.max(Math.ceil(medicalFiles.length / rowsPerPage) - 1, 0);

    $: if (currentPage > lastPage) {
        currentPage = lastPage;
    }

    const getFullName = (medicalFile: MedicalFile) => {
        return `${medicalFile.patient?.firstName} ${medicalFile.patient?.lastName}`;
    };

    type ColumnType =
        | "insuranceNumber"
        | "email"
        | "patient"
        | "personalNumber";
    let sort: ColumnType = "insuranceNumber";

    let sortDirection: Lowercase<keyof typeof SortValue> = "ascending";

    function handleSort() {
        medicalFiles.sort((a: MedicalFile, b: MedicalFile) => {
            let aVal: any, bVal: any;

            if (sort === "patient") {
                aVal = getFullName(a);
                bVal = getFullName(b);
            } else if (sort === "email") {
                aVal = a.patient?.coordinate?.email;
                bVal = b.patient?.coordinate?.email;
            } else if (sort === "personalNumber") {
                aVal = Number(
                    a.patient?.coordinate?.personalPhoneNumber?.replace(
                        /\D/g,
                        "",
                    ),
                );
                bVal = Number(
                    b.patient?.coordinate?.personalPhoneNumber?.replace(
                        /\D/g,
                        "",
                    ),
                );
            } else {
                aVal = a[sort];
                bVal = b[sort];
            }
            if (!aVal && !bVal) return 0;
            if (!aVal || aVal === "null") return 1;
            if (!bVal || bVal === "null") return -1;

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
        medicalFiles = medicalFiles;
    }

    onMount(() => {
        handleSort();
    });
</script>

<svelte:head>
    <title>Medical Files</title>
</svelte:head>

<DataTable
    sortable
    bind:sort
    bind:sortDirection
    on:SMUIDataTable:sorted={handleSort}
    table$aria-label="Medical File list"
    style="width: 100%;"
>
    <Head>
        <Row>
            <Cell columnId="insuranceNumber">
                <Label>Insurance Number</Label>
                <IconButton class="material-icons">arrow_upward</IconButton>
            </Cell>
            <Cell columnId="patient">
                <Label>Patient</Label>
                <IconButton class="material-icons">arrow_upward</IconButton>
            </Cell>
            <Cell columnId="email">
                <Label>Email</Label>
                <IconButton class="material-icons">arrow_upward</IconButton>
            </Cell>
            <Cell columnId="personalNumber">
                <Label>Personal Number</Label>
                <IconButton class="material-icons">arrow_upward</IconButton>
            </Cell>
        </Row>
    </Head>
    <Body>
        {#each slice as medicalFile (medicalFile.id)}
            <Row>
                <Cell>
                    <a href={`/medicalfiles/${medicalFile.id}`}>
                        {medicalFile.insuranceNumber}
                    </a>
                </Cell>
                <Cell>{getFullName(medicalFile)}</Cell>
                <Cell>{medicalFile.patient.coordinate.email}</Cell>
                <Cell>{medicalFile.patient.coordinate.personalPhoneNumber}</Cell
                >
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
            {start + 1}-{end} of {medicalFiles.length}
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

<style>
</style>
