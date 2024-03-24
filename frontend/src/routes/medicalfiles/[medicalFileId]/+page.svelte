<script lang="ts">

// import type { PageData } from './$types';

export let data: PageServerData;

$: ({ medicalFile } = data);

</script>

    <svelte:head>
        <title>Medical File {medicalFile.insuranceNumber}</title>
    </svelte:head>

    <h1>Medical File {medicalFile.insuranceNumber}</h1>

    <dl>
        <dt>Insurance Number</dt>
        <dd>{medicalFile.insuranceNumber}</dd>

        <dt>Patient</dt>
        <dd>{medicalFile.patient?.firstName} {medicalFile.patient?.lastName}</dd>
    </dl>   