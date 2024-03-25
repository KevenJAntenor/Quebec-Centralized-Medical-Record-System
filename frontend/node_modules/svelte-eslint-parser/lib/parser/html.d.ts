export type AttributeToken = {
    key: AttributeKeyToken;
    value: AttributeValueToken | null;
};
export type AttributeKeyToken = {
    name: string;
    start: number;
    end: number;
};
export type AttributeValueToken = {
    value: string;
    quote: '"' | "'" | null;
    start: number;
    end: number;
};
/** Parse HTML attributes */
export declare function parseAttributes(code: string, startIndex: number): {
    attributes: AttributeToken[];
    index: number;
};
