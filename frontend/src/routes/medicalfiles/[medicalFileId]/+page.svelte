<script lang="ts">
	  import type { MedicalFile } from '$lib/types/medicalFile';
      import type { MedicalVisit } from '$lib/types/medicalVisit';
    import type { PageData } from './$types';
    import DataTable, {
    Head,
    Body,
    Row,
    Cell,
    Label,
    SortValue,
    Pagination,
  } from '@smui/data-table';
  import IconButton from '@smui/icon-button';
  import Paper, { Title, Subtitle, Content } from '@smui/paper';
  // import { Label } from '@smui/common';
  import Select, { Option } from '@smui/select';
// import type { PageData } from './$types';

export let data: PageServerData;

$: ({ medicalFile } = data);

$: medicalVisitList = medicalFile.medicalVisitList;


let rowsPerPage = 10;
  let currentPage = 0;

  $: start = currentPage * rowsPerPage;
  $: end = Math.min(start + rowsPerPage, medicalVisitList.length);
  $: slice = medicalVisitList.slice(start, end);
  $: lastPage = Math.max(Math.ceil(medicalVisitList.length / rowsPerPage) - 1, 0);

  $: if (currentPage > lastPage) {
    currentPage = lastPage;
  }




let sort: keyof MedicalVisit = 'id';
  let sortDirection: Lowercase<keyof typeof SortValue> = 'ascending';

    function handleSort() {
    medicalVisitList.sort((a: MedicalVisit, b: MedicalVisit) => {
        let aVal: any, bVal: any;
        if (sort === 'establishment') {
            aVal = a.establishment;
            bVal = b.establishment;
        } else if (sort === 'doctor') {
            aVal = a.doctor;
            bVal = b.doctor;
        } else if (sort === 'dateOfVisit') {
            aVal = a.dateOfVisit;
            bVal = b.dateOfVisit;
        } else if (sort === 'diagnostic') {
            aVal = a.diagnostic;
            bVal = b.diagnostic;
        } else if (sort === 'treatment') {
            aVal = a.treatment;
            bVal = b.treatment;
        }

        const [sortedAVal, sortedBVal] = [aVal, bVal][
            sortDirection === 'ascending' ? 'slice' : 'reverse'
        ]();
        if (typeof sortedAVal === 'string' && typeof sortedBVal === 'string') {
            return sortedAVal.localeCompare(sortedBVal);
        }
        return Number(sortedAVal) - Number(sortedBVal);
    });
    medicalVisitList = medicalVisitList;
}

</script>

    <svelte:head>
        <title>Medical File {medicalFile.insuranceNumber}</title>
    </svelte:head>

    <div class="paper-container">
        <Paper>
          <Title>Medical File {medicalFile.insuranceNumber}</Title>
          <Subtitle>Patient</Subtitle>
          <Content>{medicalFile.patient?.firstName} {medicalFile.patient?.lastName}</Content>
        </Paper>
        <Paper>
          <Title>Medical Visit List</Title>
        </Paper>



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
      {#each medicalVisitList as medicalVisit (medicalVisit.id)}
        <Row>
          <Cell>
            <a href={`/medicalfiles/${medicalFile.id}/${medicalVisit.id}`}>
            {medicalVisit.establishment}
            </a>
          </Cell>
          <Cell>{medicalVisit.doctor}</Cell>
          <Cell>{medicalVisit.dateOfVisit}</Cell>
          <Cell>{medicalVisit.diagnostic}</Cell>
          <Cell>{medicalVisit.treatment}</Cell>
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
    </Paper>
    

</div>