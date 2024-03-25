import type * as SvAST from "../svelte-ast-types";
import type { SvelteAwaitBlock, SvelteEachBlock, SvelteIfBlock, SvelteIfBlockAlone, SvelteIfBlockElseIf, SvelteKeyBlock } from "../../ast";
import type { Context } from "../../context";
export declare function convertIfBlock(node: SvAST.IfBlock, parent: SvelteIfBlock["parent"], ctx: Context): SvelteIfBlockAlone;
export declare function convertIfBlock(node: SvAST.IfBlock, parent: SvelteIfBlock["parent"], ctx: Context, elseif: true): SvelteIfBlockElseIf;
/** Convert for EachBlock */
export declare function convertEachBlock(node: SvAST.EachBlock, parent: SvelteEachBlock["parent"], ctx: Context): SvelteEachBlock;
/** Convert for AwaitBlock */
export declare function convertAwaitBlock(node: SvAST.AwaitBlock, parent: SvelteAwaitBlock["parent"], ctx: Context): SvelteAwaitBlock;
/** Convert for KeyBlock */
export declare function convertKeyBlock(node: SvAST.KeyBlock, parent: SvelteKeyBlock["parent"], ctx: Context): SvelteKeyBlock;
