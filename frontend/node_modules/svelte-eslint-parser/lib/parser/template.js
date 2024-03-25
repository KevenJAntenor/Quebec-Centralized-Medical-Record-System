"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseTemplate = void 0;
const compiler_1 = require("svelte/compiler");
const index_1 = require("./converts/index");
const sort_1 = require("./sort");
const __1 = require("..");
/**
 * Parse for template
 */
function parseTemplate(code, ctx, parserOptions = {}) {
    try {
        const svelteAst = (0, compiler_1.parse)(code, {
            filename: parserOptions.filePath,
        });
        const ast = (0, index_1.convertSvelteRoot)(svelteAst, ctx);
        (0, sort_1.sortNodes)(ast.body);
        return {
            ast,
            svelteAst,
        };
    }
    catch (e) {
        if (typeof e.pos === "number") {
            const err = new __1.ParseError(e.message, e.pos, ctx);
            err.svelteCompilerError = e;
            throw err;
        }
        throw e;
    }
}
exports.parseTemplate = parseTemplate;
