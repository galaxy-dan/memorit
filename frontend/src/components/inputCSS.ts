export function transitionCss(): string {
  return 'transition ease-in-out duration-[150ms] ';
}

export const transitionCss2 = 'transition ease-in-out duration-[150ms] ';

export function transitionCssSlower(): string {
  return 'transition ease-in-out duration-[1000ms] ';
}

export function iconCss(cond1: boolean, cond2: boolean): string {
  return ` mr-2 text-3xl ${
    (cond1 || cond2) && 'text-red-200 '
  } ${transitionCss()} `;
}

export function containerCss(): string {
  return 'mx-2 px-3 py-2 border rounded-xl bg-white my-[0.65rem]';
}

export function innerShadow(): string {
  return 'shadow-[inset_0_0_3px_3px_rgba(0,0,0,0.2)]';
}
