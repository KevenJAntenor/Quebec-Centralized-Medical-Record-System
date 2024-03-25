"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.convertAttributeValueTokenToLiteral = exports.convertTextToLiteral = exports.convertText = void 0;
/** Convert for Text */
function convertText(node, parent, ctx) {
    const text = Object.assign({ type: "SvelteText", value: node.data, parent }, ctx.getConvertLocation(node));
    extractTextTokens(node, ctx);
    return text;
}
exports.convertText = convertText;
/** Convert for Text to Literal */
function convertTextToLiteral(node, parent, ctx) {
    const text = Object.assign({ type: "SvelteLiteral", value: node.data, parent }, ctx.getConvertLocation(node));
    extractTextTokens(node, ctx);
    return text;
}
exports.convertTextToLiteral = convertTextToLiteral;
/** Convert for AttributeValueToken to Literal */
function convertAttributeValueTokenToLiteral(node, parent, ctx) {
    const valueLoc = node.quote
        ? { start: node.start + 1, end: node.end - 1 }
        : node;
    const text = Object.assign({ type: "SvelteLiteral", value: node.value, parent }, ctx.getConvertLocation(valueLoc));
    extractTextTokens(valueLoc, ctx);
    return text;
}
exports.convertAttributeValueTokenToLiteral = convertAttributeValueTokenToLiteral;
/** Extract tokens */
function extractTextTokens(node, ctx) {
    const loc = node;
    let start = loc.start;
    let word = false;
    for (let index = loc.start; index < loc.end; index++) {
        if (word !== Boolean(ctx.code[index].trim())) {
            if (start < index) {
                ctx.addToken("HTMLText", { start, end: index });
            }
            word = !word;
            start = index;
        }
    }
    if (start < loc.end) {
        ctx.addToken("HTMLText", { start, end: loc.end });
    }
}
