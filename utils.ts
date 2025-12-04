/**
 * Utility helper functions for general-purpose TypeScript/JavaScript projects.
 *
 * This file is framework-agnostic and can be imported from Node or browser code.
 * None of the helpers depend on external libraries.
 */

/** Clamp a number between min and max (inclusive). If min > max they are swapped. */
export function clamp(n: number, min: number, max: number): number {
  if (!Number.isFinite(n) || !Number.isFinite(min) || !Number.isFinite(max)) {
    throw new TypeError("clamp expects finite numbers");
  }
  if (min > max) [min, max] = [max, min];
  return Math.min(Math.max(n, min), max);
}

/** Sleep for the specified milliseconds. */
export function sleep(ms: number): Promise<void> {
  if (!Number.isFinite(ms) || ms < 0) {
    throw new TypeError("sleep expects a non-negative finite number of milliseconds");
  }
  return new Promise((resolve) => setTimeout(resolve, ms));
}

/** Capitalize the first character and lower-case the rest. */
export function capitalize(s: string): string {
  if (s.length === 0) return s;
  return s.charAt(0).toUpperCase() + s.slice(1).toLowerCase();
}

/** Convert a string to camelCase. */
export function toCamelCase(input: string): string {
  const words = splitWords(input);
  if (words.length === 0) return "";
  const [first, ...rest] = words.map((w) => w.toLowerCase());
  return [first, ...rest.map(capitalize)].join("");
}

/** Convert a string to kebab-case. */
export function toKebabCase(input: string): string {
  const words = splitWords(input);
  return words.map((w) => w.toLowerCase()).join("-");
}

/** Create a numeric range. range(5) -> [0,1,2,3,4]; range(1,5) -> [1,2,3,4] */
export function range(start: number, end?: number, step = 1): number[] {
  if (end === undefined) {
    end = start;
    start = 0;
  }
  if (step === 0) throw new Error("range step cannot be 0");
  const result: number[] = [];
  const increasing = end >= start;
  if (!increasing && step > 0) step = -step;
  for (
    let i = start;
    increasing ? i < (end as number) : i > (end as number);
    i += step
  ) {
    result.push(i);
  }
  return result;
}

/** Return a new array with unique elements, preserving order. */
export function unique<T>(arr: T[]): T[] {
  return Array.from(new Set(arr));
}

/** Simple memoization for pure functions based on JSON-serializable arguments. */
export function memoize<F extends (...args: any[]) => any>(fn: F): F {
  const cache = new Map<string, any>();
  const memoized = function (this: any, ...args: any[]) {
    let key: string;
    try {
      key = JSON.stringify(args);
    } catch {
      // Fallback for non-serializable args
      key = args.map((a) => String(a)).join("|\u241F|");
    }
    if (cache.has(key)) return cache.get(key);
    const val = fn.apply(this, args);
    cache.set(key, val);
    return val;
  } as unknown as F;
  return memoized;
}

/** Determine if a value is null or undefined. */
export function isNil(value: unknown): value is null | undefined {
  return value === null || value === undefined;
}

/** Determine if a value is considered empty. */
export function isEmpty(value: unknown): boolean {
  if (isNil(value)) return true;
  if (typeof value === "string") return value.trim().length === 0;
  if (Array.isArray(value)) return value.length === 0;
  if (value instanceof Map || value instanceof Set) return value.size === 0;
  if (isPlainObject(value)) return Object.keys(value as object).length === 0;
  return false;
}

/** Deeply merge two plain objects. Arrays are concatenated. Does not handle circular refs. */
export function deepMerge<T extends Record<string, any>, U extends Record<string, any>>(
  target: T,
  source: U
): T & U {
  if (!isPlainObject(target) || !isPlainObject(source)) {
    throw new TypeError("deepMerge expects plain objects");
  }
  const out: Record<string, any> = { ...target };
  for (const [key, value] of Object.entries(source)) {
    const existing = (out as any)[key];
    if (Array.isArray(existing) && Array.isArray(value)) {
      (out as any)[key] = [...existing, ...value];
    } else if (isPlainObject(existing) && isPlainObject(value)) {
      (out as any)[key] = deepMerge(existing, value);
    } else {
      (out as any)[key] = value;
    }
  }
  return out as T & U;
}

/** Ensure the provided function is only called once. Subsequent calls return the first result. */
export function once<F extends (...args: any[]) => any>(fn: F): F {
  let called = false;
  let result: any;
  const wrapped = function (this: any, ...args: any[]) {
    if (!called) {
      called = true;
      result = fn.apply(this, args);
    }
    return result;
  } as unknown as F;
  return wrapped;
}

/** Generate a v4-like UUID using crypto if available, else a fallback. */
export function uuid(): string {
  if (typeof crypto !== "undefined" && typeof (crypto as any).randomUUID === "function") {
    return (crypto as any).randomUUID();
  }
  // Fallback RFC4122-ish implementation
  // eslint-disable-next-line no-bitwise
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, (c) => {
    const r = (Math.random() * 16) | 0;
    // eslint-disable-next-line no-bitwise
    const v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}

/** Internal: split a string into words by non-alphanumeric boundaries, keeping numbers. */
function splitWords(input: string): string[] {
  return input
    .trim()
    .replace(/([a-z])([A-Z])/g, "$1 $2")
    .split(/[^0-9A-Za-z]+/)
    .filter(Boolean);
}

/** Type guard for plain objects (excluding arrays, null, class instances). */
export function isPlainObject(value: unknown): value is Record<string, unknown> {
  if (value === null || typeof value !== "object") return false;
  if (Array.isArray(value)) return false;
  const proto = Object.getPrototypeOf(value);
  return proto === Object.prototype || proto === null;
}
