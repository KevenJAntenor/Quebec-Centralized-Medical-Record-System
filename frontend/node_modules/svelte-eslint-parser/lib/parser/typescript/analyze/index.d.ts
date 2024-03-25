import { VirtualTypeScriptContext } from "../context";
import type { SvelteHTMLElement } from "../../../ast";
export type AnalyzeTypeScriptContext = {
    slots: Set<SvelteHTMLElement>;
};
/**
 * Analyze TypeScript source code.
 * Generate virtual code to provide correct type information for Svelte store reference namess and scopes.
 * See https://github.com/sveltejs/svelte-eslint-parser/blob/main/docs/internal-mechanism.md#scope-types
 */
export declare function analyzeTypeScript(code: {
    script: string;
    render: string;
}, attrs: Record<string, string | undefined>, parserOptions: any, context: AnalyzeTypeScriptContext): VirtualTypeScriptContext;
