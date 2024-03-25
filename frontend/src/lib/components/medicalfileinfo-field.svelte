<script lang="ts">
    export let name: string;
    export let value: unknown = undefined;
    export let lineBreaksAllowed = false;
  </script>
  
  <div class={'item'}>
    <h3>{name}</h3>
    {#if value && lineBreaksAllowed}
      <span>{value}</span>
    {:else if value}
      <span>{value}</span>
    {:else}
      <span class="notset">Not specified</span>
    {/if}
  </div>
  
  <style>
    .item {
      display: flex;
      margin: 1rem;
    }
  
    span {
      align-self: center;
      white-space: pre-wrap;
    }
  
    .notset {
      color: var(--text-light);
    }
  
    h3 {
      margin-right: 1rem;
    }
  
    @media (max-width: 576px) {
      .item {
        display: block;
        margin: 0.5rem;
      }
  
      span {
        margin-left: 0.5rem;
      }
    }
  </style>
  