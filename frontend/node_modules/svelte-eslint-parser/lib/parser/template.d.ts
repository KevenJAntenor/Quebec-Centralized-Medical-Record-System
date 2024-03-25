import type * as SvAST from "./svelte-ast-types";
import type { Context } from "../context";
import type { SvelteProgram } from "../ast";
/**
 * Parse for template
 */
export declare function parseTemplate(code: string, ctx: Context, parserOptions?: any): {
    ast: SvelteProgram;
    svelteAst: SvAST.Ast;
};
