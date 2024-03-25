export type SvelteActionReturnType<P> = {
    update?: (newParams?: P) => void;
    destroy?: () => void;
} | void;
export type SvelteHTMLActionType<P> = (node: HTMLElement, params?: P) => SvelteActionReturnType<P>;
export type HTMLActionEntry<P extends any = any> = SvelteHTMLActionType<P> | [SvelteHTMLActionType<P>, P];
export type HTMLActionArray = HTMLActionEntry[];
export type SvelteSVGActionType<P> = (node: SVGElement, params?: P) => SvelteActionReturnType<P>;
export type SVGActionEntry<P extends any = any> = SvelteSVGActionType<P> | [SvelteSVGActionType<P>, P];
export type SVGActionArray = SVGActionEntry[];
export type ActionArray = HTMLActionArray | SVGActionArray;
export declare function useActions(node: HTMLElement | SVGElement, actions: ActionArray): {
    update(actions: ActionArray): void;
    destroy(): void;
};
