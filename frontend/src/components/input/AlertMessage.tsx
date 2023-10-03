import React, { ReactNode } from 'react'
import { containerMX } from './inputCSS'

type Props={
    children:ReactNode,
}
export default function AlertMessage({children}:Props) {
  return (
    <div className={`text-xl text-red-500 `+containerMX}>{children}</div>
  )
}
