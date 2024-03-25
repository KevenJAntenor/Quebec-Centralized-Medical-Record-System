import type { Comment, Locations, SvelteElement, SvelteHTMLElement, SvelteScriptElement, SvelteStyleElement, Token } from "../ast";
import type ESTree from "estree";
import type * as SvAST from "../parser/svelte-ast-types";
import { ScriptLetContext } from "./script-let";
import { LetDirectiveCollections } from "./let-directive-collection";
import type { AttributeToken } from "../parser/html";
export declare class ScriptsSourceCode {
    private raw;
    private trimmedRaw;
    readonly attrs: Record<string, string | undefined>;
    private _separate;
    private _appendScriptLets;
    separateIndexes: number[];
    constructor(script: string, attrs: Record<string, string | undefined>);
    getCurrentVirtualCode(): string;
    getCurrentVirtualCodeInfo(): {
        script: string;
        render: string;
    };
    getCurrentVirtualCodeLength(): number;
    addLet(letCode: string): {
        start: number;
        end: number;
    };
    stripCode(start: number, end: number): void;
}
export type ContextSourceCode = {
    template: string;
    scripts: ScriptsSourceCode;
};
export declare class Context {
    readonly code: string;
    readonly parserOptions: any;
    readonly sourceCode: ContextSourceCode;
    readonly tokens: Token[];
    readonly comments: Comment[];
    private readonly locs;
    private readonly locsMap;
    readonly scriptLet: ScriptLetContext;
    readonly letDirCollections: LetDirectiveCollections;
    readonly slots: Set<SvelteHTMLElement>;
    readonly elements: Map<SvelteElement, SvAST.Element | SvAST.InlineComponent | SvAST.Window | SvAST.Document | SvAST.Body | SvAST.Head | SvAST.Title | SvAST.Options | SvAST.SlotTemplate | SvAST.Slot>;
    private readonly state;
    private readonly blocks;
    constructor(code: string, parserOptions: any);
    getLocFromIndex(index: number): {
        line: number;
        column: number;
    };
    getIndexFromLoc(loc: {
        line: number;
        column: number;
    }): number;
    /**
     * Get the location information of the given node.
     * @param node The node.
     */
    getConvertLocation(node: {
        start: number;
        end: number;
    } | ESTree.Node): Locations;
    addComment(comment: Comment): void;
    /**
     * Add token to tokens
     */
    addToken(type: Token["type"], range: {
        start: number;
        end: number;
    }): Token;
    /**
     * get text
     */
    getText(range: {
        start: number;
        end: number;
    } | ESTree.Node): string;
    isTypeScript(): boolean;
    stripScriptCode(start: number, end: number): void;
    findBlock(element: SvelteScriptElement | SvelteStyleElement | SvelteElement): Block | undefined;
    findSelfClosingBlock(element: SvelteElement): SelfClosingBlock | undefined;
}
type Block = {
    tag: "script" | "style" | "template";
    originalTag: string;
    attrs: AttributeToken[];
    selfClosing?: false;
    contentRange: [number, number];
    startTagRange: [number, number];
    endTagRange: [number, number];
} | SelfClosingBlock;
type SelfClosingBlock = {
    tag: "script" | "style" | "template";
    originalTag: string;
    attrs: AttributeToken[];
    selfClosing: true;
    startTagRange: [number, number];
};
export declare class LinesAndColumns {
    private readonly lineStartIndices;
    constructor(code: string);
    getLocFromIndex(index: number): {
        line: number;
        column: number;
    };
    getIndexFromLoc(loc: {
        line: number;
        column: number;
    }): number;
    /**
     * Get the location information of the given indexes.
     */
    getLocations(start: number, end: number): Locations;
}
export {};
