import type { SvelteAttribute, SvelteShorthandAttribute, SvelteDirective, SvelteSpreadAttribute, SvelteStartTag, SvelteStyleDirective } from "../../ast";
import type { Context } from "../../context";
import type * as SvAST from "../svelte-ast-types";
import type { AttributeToken } from "../html";
/** Convert for Attributes */
export declare function convertAttributes(attributes: SvAST.AttributeOrDirective[], parent: SvelteStartTag, ctx: Context): IterableIterator<SvelteAttribute | SvelteShorthandAttribute | SvelteSpreadAttribute | SvelteDirective | SvelteStyleDirective>;
/** Convert for attribute tokens */
export declare function convertAttributeTokens(attributes: AttributeToken[], parent: SvelteStartTag, ctx: Context): IterableIterator<SvelteAttribute>;
