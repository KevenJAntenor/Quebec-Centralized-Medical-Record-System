<script lang="ts">
    import MedicalFileInfo from '$lib/components/medical-file-info.svelte';
    import type { PageData } from './$types';

    export let data: PageData;
  
    $: ({ medicalFiles } = data);
</script>
  
  <svelte:head>
    <title>Medical Files</title>
  </svelte:head>
  
<section>
    <h1>Medical Files</h1>
    
    {#if medicalFiles.length === 0}
        <p>No medical files in records.</p>
    {:else}
        {#each medicalFiles as medicalFile (medicalFile.id)}
            <MedicalFileInfo {medicalFile} />
        {/each}
    {/if}
</section>
  
  <style>
    section {
      padding: 3rem 1.5rem;
    }
  
    h1 {
      font-size: 2.5rem;
      margin: 0 0 1.5rem 0;
      text-align: center;
    }
  </style>
  