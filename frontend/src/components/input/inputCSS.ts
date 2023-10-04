export const transitionCss = 'transition ease-in-out duration-[150ms] ';

export const transitionCssSlower = 'transition ease-in-out duration-[1000ms] ';

export function iconCss(cond1: boolean, cond2: boolean): string {
  return ` mr-2 text-3xl ${
    (cond1 || cond2) && 'text-blue-400 '
  } ${transitionCss} `;
}

export const containerMX = 'mx-5';

export const containerCss =
  `${containerMX} px-3 py-2 border-2 rounded-xl bg-white my-[0.5rem]`;

export const innerShadow = 'shadow-[inset_0_0_3px_3px_rgba(0,0,0,0.2)]';
