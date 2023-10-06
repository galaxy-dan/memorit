import { useEffect } from 'react';

function useCustomBack(customBack: () => void) {
  useEffect(() => {
    // 뒤로가기 제어
    (() => {
      history.pushState(null, '', location.href);
      window.addEventListener('popstate', customBack);
    })();

    return () => {
      window.removeEventListener('popstate', customBack);
    };
  }, [customBack]);
}

export default useCustomBack;
