import { parseRegexp } from '@vitest/utils';

const REGEXP_WRAP_PREFIX = "$$vitest:";
function createThreadsRpcOptions({ port }) {
  return {
    post: (v) => {
      port.postMessage(v);
    },
    on: (fn) => {
      port.addListener("message", fn);
    }
  };
}
function createForksRpcOptions(nodeV8) {
  return {
    serialize: nodeV8.serialize,
    deserialize: (v) => nodeV8.deserialize(Buffer.from(v)),
    post(v) {
      process.send(v);
    },
    on(fn) {
      process.on("message", (message, ...extras) => {
        if (message == null ? void 0 : message.__tinypool_worker_message__)
          return;
        return fn(message, ...extras);
      });
    }
  };
}
function unwrapSerializableConfig(config) {
  if (config.testNamePattern && typeof config.testNamePattern === "string") {
    const testNamePattern = config.testNamePattern;
    if (testNamePattern.startsWith(REGEXP_WRAP_PREFIX))
      config.testNamePattern = parseRegexp(testNamePattern.slice(REGEXP_WRAP_PREFIX.length));
  }
  if (config.defines && Array.isArray(config.defines.keys) && config.defines.original) {
    const { keys, original } = config.defines;
    const defines = {};
    for (const key of keys)
      defines[key] = original[key];
    config.defines = defines;
  }
  return config;
}

export { createThreadsRpcOptions as a, createForksRpcOptions as c, unwrapSerializableConfig as u };
