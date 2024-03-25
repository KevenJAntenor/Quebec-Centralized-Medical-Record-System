import type ESTree from "estree";
import type { ScopeManager } from "eslint-scope";
import type { SvelteScriptElement } from "../ast";
/**
 * Analyze scope
 */
export declare function analyzeScope(node: ESTree.Node, parserOptions?: any): ScopeManager;
/** Analyze reactive scope */
export declare function analyzeReactiveScope(scopeManager: ScopeManager): void;
/**
 * Analyze store scope. e.g. $count
 */
export declare function analyzeStoreScope(scopeManager: ScopeManager): void;
/** Transform props exports */
export declare function analyzePropsScope(body: SvelteScriptElement, scopeManager: ScopeManager): void;
