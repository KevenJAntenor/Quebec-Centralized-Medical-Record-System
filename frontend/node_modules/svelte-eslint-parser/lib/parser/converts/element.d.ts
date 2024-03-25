import type { SvelteAwaitBlock, SvelteAwaitCatchBlock, SvelteAwaitPendingBlock, SvelteAwaitThenBlock, SvelteConstTag, SvelteDebugTag, SvelteEachBlock, SvelteElement, SvelteElseBlockAlone, SvelteHTMLComment, SvelteIfBlock, SvelteIfBlockAlone, SvelteKeyBlock, SvelteMustacheTag, SvelteProgram, SvelteScriptElement, SvelteStyleElement, SvelteText } from "../../ast";
import type { Context } from "../../context";
import type * as SvAST from "../svelte-ast-types";
/** Convert for Fragment or Element or ... */
export declare function convertChildren(fragment: {
    children: SvAST.TemplateNode[];
}, parent: SvelteProgram | SvelteElement | SvelteIfBlock | SvelteElseBlockAlone | SvelteEachBlock | SvelteAwaitPendingBlock | SvelteAwaitThenBlock | SvelteAwaitCatchBlock | SvelteKeyBlock, ctx: Context): IterableIterator<SvelteText | SvelteElement | SvelteMustacheTag | SvelteDebugTag | SvelteConstTag | SvelteIfBlockAlone | SvelteEachBlock | SvelteAwaitBlock | SvelteKeyBlock | SvelteHTMLComment>;
/** Extract element tag and tokens */
export declare function extractElementTags<E extends SvelteScriptElement | SvelteElement | SvelteStyleElement>(element: E, ctx: Context, options: {
    buildNameNode: (openTokenRange: {
        start: number;
        end: number;
    }) => E["name"];
    extractAttribute?: boolean;
}): void;
