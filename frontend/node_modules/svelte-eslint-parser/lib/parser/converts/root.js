"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.convertSvelteRoot = void 0;
const element_1 = require("./element");
const attr_1 = require("./attr");
/**
 * Convert root
 */
function convertSvelteRoot(svelteAst, ctx) {
    const ast = Object.assign({ type: "Program", body: [], comments: ctx.comments, sourceType: "module", tokens: ctx.tokens, parent: null }, ctx.getConvertLocation({ start: 0, end: ctx.code.length }));
    const body = ast.body;
    if (svelteAst.html) {
        const fragment = svelteAst.html;
        body.push(...(0, element_1.convertChildren)(fragment, ast, ctx));
    }
    if (svelteAst.instance) {
        const instance = svelteAst.instance;
        const script = Object.assign({ type: "SvelteScriptElement", name: null, startTag: null, body: [], endTag: null, parent: ast }, ctx.getConvertLocation(instance));
        extractAttributes(script, ctx);
        (0, element_1.extractElementTags)(script, ctx, {
            buildNameNode: (openTokenRange) => {
                ctx.addToken("HTMLIdentifier", openTokenRange);
                const name = Object.assign({ type: "SvelteName", name: "script", parent: script }, ctx.getConvertLocation(openTokenRange));
                return name;
            },
        });
        body.push(script);
    }
    if (svelteAst.module) {
        const module = svelteAst.module;
        const script = Object.assign({ type: "SvelteScriptElement", name: null, startTag: null, body: [], endTag: null, parent: ast }, ctx.getConvertLocation(module));
        extractAttributes(script, ctx);
        (0, element_1.extractElementTags)(script, ctx, {
            buildNameNode: (openTokenRange) => {
                ctx.addToken("HTMLIdentifier", openTokenRange);
                const name = Object.assign({ type: "SvelteName", name: "script", parent: script }, ctx.getConvertLocation(openTokenRange));
                return name;
            },
        });
        body.push(script);
    }
    if (svelteAst.css) {
        const style = Object.assign({ type: "SvelteStyleElement", name: null, startTag: null, children: [], endTag: null, parent: ast }, ctx.getConvertLocation(svelteAst.css));
        extractAttributes(style, ctx);
        (0, element_1.extractElementTags)(style, ctx, {
            buildNameNode: (openTokenRange) => {
                ctx.addToken("HTMLIdentifier", openTokenRange);
                const name = Object.assign({ type: "SvelteName", name: "style", parent: style }, ctx.getConvertLocation(openTokenRange));
                return name;
            },
        });
        if (style.endTag && style.startTag.range[1] < style.endTag.range[0]) {
            const contentRange = {
                start: style.startTag.range[1],
                end: style.endTag.range[0],
            };
            ctx.addToken("HTMLText", contentRange);
            style.children = [
                Object.assign({ type: "SvelteText", value: ctx.code.slice(contentRange.start, contentRange.end), parent: style }, ctx.getConvertLocation(contentRange)),
            ];
        }
        body.push(style);
    }
    // Set the scope of the Program node.
    ctx.scriptLet.addProgramRestore((node, _tokens, _comments, { scopeManager, registerNodeToScope, addPostProcess }) => {
        const scopes = [];
        for (const scope of scopeManager.scopes) {
            if (scope.block === node) {
                registerNodeToScope(ast, scope);
                scopes.push(scope);
            }
        }
        addPostProcess(() => {
            // Reverts the node indicated by `block` to the original Program node.
            // This state is incorrect, but `eslint-utils`'s `referenceTracker.iterateEsmReferences()` tracks import statements
            // from Program nodes set to `block` in global scope. This can only be handled by the original Program node.
            scopeManager.globalScope.block = node;
        });
    });
    return ast;
}
exports.convertSvelteRoot = convertSvelteRoot;
/** Extract attrs */
function extractAttributes(element, ctx) {
    element.startTag = {
        type: "SvelteStartTag",
        attributes: [],
        selfClosing: false,
        parent: element,
        range: [element.range[0], null],
        loc: {
            start: {
                line: element.loc.start.line,
                column: element.loc.start.column,
            },
            end: null,
        },
    };
    const block = ctx.findBlock(element);
    if (block) {
        element.startTag.attributes.push(...(0, attr_1.convertAttributeTokens)(block.attrs, element.startTag, ctx));
    }
}
