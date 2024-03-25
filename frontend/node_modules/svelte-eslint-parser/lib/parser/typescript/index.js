"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseTypeScript = void 0;
const script_1 = require("../script");
const analyze_1 = require("./analyze");
/**
 * Parse for type script
 */
function parseTypeScript(code, attrs, parserOptions, context) {
    const tsCtx = (0, analyze_1.analyzeTypeScript)(code, attrs, parserOptions, context);
    const result = (0, script_1.parseScript)(tsCtx.script, attrs, parserOptions);
    tsCtx.restoreContext.restore(result);
    return result;
}
exports.parseTypeScript = parseTypeScript;
