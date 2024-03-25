import type * as SvAST from "../svelte-ast-types";
import type { SvelteProgram } from "../../ast";
import type { Context } from "../../context";
/**
 * Convert root
 */
export declare function convertSvelteRoot(svelteAst: SvAST.Ast, ctx: Context): SvelteProgram;
