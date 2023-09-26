'use client';

import { CSSProperties } from 'react';
import GridLoader from 'react-spinners/GridLoader';

const override: CSSProperties = {
  display: 'block',
  margin: '0 auto',
  borderColor: 'red',
};

export default function Loading() {
  return (
    <div className="flex h-[60vh] justify-center items-center">
      <div>
        <GridLoader size={100} color="#4ade80" cssOverride={override} />
      </div>
    </div>
  );
}
