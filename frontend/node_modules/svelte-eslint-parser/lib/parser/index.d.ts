import type { Comment, SvelteProgram, Token } from "../ast";
import type { Program } from "estree";
import type { ScopeManager } from "eslint-scope";
import type * as SvAST from "./svelte-ast-types";
import { type StyleContext, type StyleContextNoStyleElement, type StyleContextParseError, type StyleContextSuccess, type StyleContextUnknownLang } from "./style-context";
export { StyleContext, StyleContextNoStyleElement, StyleContextParseError, StyleContextSuccess, StyleContextUnknownLang, };
export interface ESLintProgram extends Program {
    comments: Comment[];
    tokens: Token[];
}
/**
 * The parsing result of ESLint custom parsers.
 */
export interface ESLintExtendedProgram {
    ast: ESLintProgram;
    services?: Record<string, any>;
    visitorKeys?: {
        [type: string]: string[];
    };
    scopeManager?: ScopeManager;
    _virtualScriptCode?: string;
}
/**
 * Parse source code
 */
export declare function parseForESLint(code: string, options?: any): {
    ast: SvelteProgram;
    services: Record<string, any> & {
        isSvelte: true;
        getSvelteHtmlAst: () => SvAST.Fragment;
        getStyleContext: () => StyleContext;
    };
    visitorKeys: {
        [type: string]: string[];
    };
    scopeManager: ScopeManager;
};
