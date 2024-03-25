import type { TSESTree } from "@typescript-eslint/types";
import type { TSESParseForESLintResult } from "./types";
/**
 * A function that restores the statement.
 * @param node The node to restore.
 * @param result The result of parsing.
 * @returns
 *   If `false`, it indicates that the specified node was not processed.
 *
 *   If `true`, it indicates that the specified node was processed for processing.
 *   This process will no longer be called.
 */
type RestoreStatementProcess = (node: TSESTree.Statement, result: TSESParseForESLintResult) => boolean;
export declare class RestoreContext {
    private readonly originalLocs;
    private readonly offsets;
    private readonly virtualFragments;
    private readonly restoreStatementProcesses;
    constructor(code: string);
    addRestoreStatementProcess(process: RestoreStatementProcess): void;
    addOffset(offset: {
        original: number;
        dist: number;
    }): void;
    addVirtualFragmentRange(start: number, end: number): void;
    /**
     * Restore AST nodes
     */
    restore(result: TSESParseForESLintResult): void;
    private remapLocation;
    private getRemapRange;
}
export {};
