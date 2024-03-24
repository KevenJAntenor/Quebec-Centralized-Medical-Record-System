<script lang="ts">
	  import type { MedicalFile } from '$lib/types/medicalFile';
    import type { PageData } from './$types';

    export let data: PageData;
  
    $: ({ medicalFiles } = data);

    const getFullName = (medicalFile: MedicalFile) => {
        return `${medicalFile.patient?.firstName} ${medicalFile.patient?.lastName}`;
    }

    import DataTable, {
    Head,
    Body,
    Row,
    Cell,
    Label,
    SortValue,
  } from '@smui/data-table';
  import IconButton from '@smui/icon-button';

  let sort: keyof MedicalFile = 'id';
  let sortDirection: Lowercase<keyof typeof SortValue> = 'ascending';

    function handleSort() {
  medicalFiles.sort((a: MedicalFile, b: MedicalFile) => {
    let aVal: any, bVal: any;

    if (sort === 'patient') {
      aVal = getFullName(a);
      bVal = getFullName(b);
    } else if (sort === 'email') {
      aVal = a.patient?.coordinate?.email;
      bVal = b.patient?.coordinate?.email;
    } else if (sort === 'personal-number') {
      aVal = Number(a.patient?.coordinate?.personalPhoneNumber?.replace(/\D/g, ''));
      bVal = Number(b.patient?.coordinate?.personalPhoneNumber?.replace(/\D/g, ''));
    } else {
      aVal = a[sort];
      bVal = b[sort];
    }

    const [sortedAVal, sortedBVal] = [aVal, bVal][
      sortDirection === 'ascending' ? 'slice' : 'reverse'
    ]();

    if (typeof sortedAVal === 'string' && typeof sortedBVal === 'string') {
      return sortedAVal.localeCompare(sortedBVal);
    }
    return Number(sortedAVal) - Number(sortedBVal);
  });
  medicalFiles = medicalFiles;
}
</script>
  
  <svelte:head>
    <title>Medical Files</title>
  </svelte:head>

<DataTable
  sortable
  bind:sort
  bind:sortDirection
  on:SMUIDataTable:sorted={handleSort}
  table$aria-label="User list"
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
      <Cell columnId="personal-number">
        <Label>Personal Number</Label>
        <IconButton class="material-icons">arrow_upward</IconButton>
      </Cell>
    </Row>
  </Head>
  <Body>
    {#each medicalFiles as medicalFile (medicalFile.id)}
      <Row>
        <Cell>{medicalFile.insuranceNumber}</Cell>
        <Cell>{getFullName(medicalFile)}</Cell>
        <Cell>{medicalFile.patient.coordinate.email}</Cell>
        <Cell>{medicalFile.patient.coordinate.personalPhoneNumber}</Cell>
      </Row>
    {/each}
  </Body>
</DataTable>

  <style>

  </style>
  