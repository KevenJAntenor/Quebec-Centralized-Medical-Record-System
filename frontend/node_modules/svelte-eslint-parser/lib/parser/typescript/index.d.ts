import type { ESLintExtendedProgram } from "..";
import type { AnalyzeTypeScriptContext } from "./analyze";
/**
 * Parse for type script
 */
export declare function parseTypeScript(code: {
    script: string;
    render: string;
}, attrs: Record<string, string | undefined>, parserOptions: unknown, context: AnalyzeTypeScriptContext): ESLintExtendedProgram;
