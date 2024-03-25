import type { SvelteDebugTag, SvelteMustacheTag, SvelteMustacheTagRaw, SvelteMustacheTagText } from "../../ast";
import type { Context } from "../../context";
import type * as SvAST from "../svelte-ast-types";
/** Convert for MustacheTag */
export declare function convertMustacheTag(node: SvAST.MustacheTag, parent: SvelteMustacheTag["parent"], ctx: Context): SvelteMustacheTagText;
/** Convert for MustacheTag */
export declare function convertRawMustacheTag(node: SvAST.RawMustacheTag, parent: SvelteMustacheTag["parent"], ctx: Context): SvelteMustacheTagRaw;
/** Convert for DebugTag */
export declare function convertDebugTag(node: SvAST.DebugTag, parent: SvelteDebugTag["parent"], ctx: Context): SvelteDebugTag;
