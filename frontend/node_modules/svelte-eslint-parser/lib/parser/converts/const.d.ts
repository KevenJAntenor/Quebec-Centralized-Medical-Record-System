import type { SvelteConstTag } from "../../ast";
import type { Context } from "../../context";
import type * as SvAST from "../svelte-ast-types";
/** Convert for ConstTag */
export declare function convertConstTag(node: SvAST.ConstTag, parent: SvelteConstTag["parent"], ctx: Context): SvelteConstTag;
