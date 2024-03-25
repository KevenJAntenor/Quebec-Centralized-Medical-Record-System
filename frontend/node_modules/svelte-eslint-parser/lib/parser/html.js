"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseAttributes = void 0;
const spacePattern = /\s/;
/** Parse HTML attributes */
function parseAttributes(code, startIndex) {
    const attributes = [];
    let index = startIndex;
    while (index < code.length) {
        const char = code[index];
        if (spacePattern.test(char)) {
            index++;
            continue;
        }
        if (char === ">" || (char === "/" && code[index + 1] === ">"))
            break;
        const attrData = parseAttribute(code, index);
        attributes.push(attrData.attribute);
        index = attrData.index;
    }
    return { attributes, index };
}
exports.parseAttributes = parseAttributes;
/** Parse HTML attribute */
function parseAttribute(code, startIndex) {
    // parse key
    const keyData = parseAttributeKey(code, startIndex);
    const key = keyData.key;
    let index = keyData.index;
    if (code[index] !== "=") {
        return {
            attribute: {
                key,
                value: null,
            },
            index,
        };
    }
    index++;
    // skip spaces
    while (index < code.length) {
        const char = code[index];
        if (spacePattern.test(char)) {
            index++;
            continue;
        }
        break;
    }
    // parse value
    const valueData = parseAttributeValue(code, index);
    return {
        attribute: {
            key,
            value: valueData.value,
        },
        index: valueData.index,
    };
}
/** Parse HTML attribute key */
function parseAttributeKey(code, startIndex) {
    const key = {
        name: code[startIndex],
        start: startIndex,
        end: startIndex + 1,
    };
    let index = key.end;
    while (index < code.length) {
        const char = code[index];
        if (char === "=" ||
            char === ">" ||
            (char === "/" && code[index + 1] === ">")) {
            break;
        }
        if (spacePattern.test(char)) {
            for (let i = index; i < code.length; i++) {
                const c = code[i];
                if (c === "=") {
                    return {
                        key,
                        index: i,
                    };
                }
                if (spacePattern.test(c)) {
                    continue;
                }
                return {
                    key,
                    index,
                };
            }
            break;
        }
        key.name += char;
        index++;
        key.end = index;
    }
    return {
        key,
        index,
    };
}
/** Parse HTML attribute value */
function parseAttributeValue(code, startIndex) {
    let index = startIndex;
    const maybeQuote = code[index];
    if (maybeQuote == null) {
        return {
            value: null,
            index,
        };
    }
    const quote = maybeQuote === '"' || maybeQuote === "'" ? maybeQuote : null;
    if (quote) {
        index++;
    }
    const valueFirstChar = code[index];
    if (valueFirstChar == null) {
        return {
            value: {
                value: maybeQuote,
                quote: null,
                start: startIndex,
                end: index,
            },
            index,
        };
    }
    if (valueFirstChar === quote) {
        return {
            value: {
                value: "",
                quote,
                start: startIndex,
                end: index + 1,
            },
            index: index + 1,
        };
    }
    const value = {
        value: valueFirstChar,
        quote,
        start: startIndex,
        end: index + 1,
    };
    index = value.end;
    while (index < code.length) {
        const char = code[index];
        if (quote) {
            if (quote === char) {
                index++;
                value.end = index;
                break;
            }
        }
        else if (spacePattern.test(char) ||
            char === ">" ||
            (char === "/" && code[index + 1] === ">")) {
            break;
        }
        value.value += char;
        index++;
        value.end = index;
    }
    return {
        value,
        index,
    };
}
