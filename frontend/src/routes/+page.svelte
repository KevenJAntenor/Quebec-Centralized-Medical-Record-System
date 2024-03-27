<script lang="ts">
    import { onMount } from "svelte";
    import type { PageData } from "./$types";
    import { API_URL } from "../constants";

    let backendData: string;
    onMount(async () => {
        try {
            const response = await fetch(API_URL);
            backendData = await response.text();
        } catch (error: unknown) {
            backendData = `Make sure your backend is running! Request failed with error: ${error}`;
        }
    });

    export let data: PageData;
</script>

<h1>It works!</h1>
<div>from client: {backendData ?? "loading"}</div>
<div>from svelte: {data.backendData}</div>

<style>
    h1 {
        color: var(--color-background);
    }
</style>
