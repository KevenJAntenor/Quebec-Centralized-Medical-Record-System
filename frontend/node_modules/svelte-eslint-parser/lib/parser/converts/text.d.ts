import type { SvelteLiteral, SvelteText } from "../../ast";
import type { Context } from "../../context";
import type { AttributeValueToken } from "../html";
import type * as SvAST from "../svelte-ast-types";
/** Convert for Text */
export declare function convertText(node: SvAST.Text, parent: SvelteText["parent"], ctx: Context): SvelteText;
/** Convert for Text to Literal */
export declare function convertTextToLiteral(node: SvAST.Text, parent: SvelteLiteral["parent"], ctx: Context): SvelteLiteral;
/** Convert for AttributeValueToken to Literal */
export declare function convertAttributeValueTokenToLiteral(node: AttributeValueToken, parent: SvelteLiteral["parent"], ctx: Context): SvelteLiteral;
