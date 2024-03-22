<script lang="ts">
    import Item from '$lib/components/medicalfileinfo-field.svelte';
    import type { MedicalFile } from '$lib/types/medicalFile';
  
    export let medicalFile: MedicalFile;
  </script>
  
  <div class="row desktop">
    <div class="column">
      <Item name="Id" value={medicalFile?.id} />
      <Item name="InsuranceNumber" value={medicalFile?.insuranceNumber} />
    </div>

  </div>
  
  <div class="column mobile">
    <Item name="Id" value={medicalFile?.id} />
    <Item name="InsuranceNumber" value={medicalFile?.insuranceNumber} />
  </div>
  
  <style>
    .column {
      border-radius: 0.35rem;
      background-color: var(--bg-2);
      display: flex;
      flex-flow: column wrap;
    }
    .row {
      display: flex;
      flex-flow: row wrap;
      max-width: 40rem;
      margin: 0 auto;
      padding: 1rem;
      border-radius: 6px;
      background-color: var(--bg-2);
    }
  
    @media (width >= 576px) {
      .mobile {
        display: none;
      }
      .column {
        margin: 0 auto;
      }
    }
  
    @media (max-width: 576px) {
      .desktop {
        display: none;
      }
    }
  </style>
  