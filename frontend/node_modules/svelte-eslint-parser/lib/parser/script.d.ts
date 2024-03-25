import type { ESLintExtendedProgram } from ".";
/**
 * Parse for script
 */
export declare function parseScript(code: string, attrs: Record<string, string | undefined>, parserOptions?: any): ESLintExtendedProgram;
/**
 * Parse for script without analyze scope
 */
export declare function parseScriptWithoutAnalyzeScope(code: string, attrs: Record<string, string | undefined>, options: any): ESLintExtendedProgram;
