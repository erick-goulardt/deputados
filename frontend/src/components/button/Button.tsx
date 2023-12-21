
import { ReactNode, ButtonHTMLAttributes } from 'react';
import "./Button.style.css"

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  children: ReactNode;
}

export function Button({ children, ...props } : ButtonProps) {
  return (
    <button {...props} className='button-component'>
      {children}
    </button>
  );
}

