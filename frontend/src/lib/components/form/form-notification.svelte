<script lang="ts">
  import { AlertCircleIcon, AlertTriangleIcon } from 'svelte-feather-icons';

  export let danger: boolean = false;
</script>

<div class="container {danger && 'danger'}">
  <div class="notification-icon {danger && 'danger'}">
    {#if danger}
      <AlertTriangleIcon class="danger" />
    {:else}
      <AlertCircleIcon />
    {/if}
  </div>
  <p class="notification-text {danger && 'danger'}">
    <slot />
  </p>
</div>

<style>
  .container {
    display: flex;
    align-items: center;
    padding: 1rem;
    max-width: 32rem;
    margin: 0 auto 1rem auto;
    border-radius: 0.35rem;
    color: var(--text-info);
    background-color: var(--bg-info);
  }

  .container.danger {
    background-color: var(--bg-danger);
  }

  .notification-text {
    font-style: italic;
    padding: 0 0.6rem;
  }

  .notification-icon {
    color: inherit;
    display: flex;
    flex-shrink: 0;
  }
</style>
