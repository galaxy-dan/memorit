import QueryProvider from '@/context/QueryProvider';
import './globals.css';
import type { Metadata } from 'next';
import localFont from 'next/font/local';
import RecoilProvider from '@/context/RecoilProvider';
import Footer from '@/components/Footer';

const spokaFont = localFont({
  src: [
    {
      path: '../../public/fonts/SpoqaHanSansNeo-Regular.woff2',
      weight: '400',
      style: 'normal',
    },
    {
      path: '../../public/fonts/SpoqaHanSansNeo-Medium.woff2',
      weight: '500',
      style: 'normal',
    },
    {
      path: '../../public/fonts/SpoqaHanSansNeo-Bold.woff2',
      weight: '700',
      style: 'normal',
    },
  ],
});

export const metadata: Metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ko" className={spokaFont.className}>
      <body className="w-full">
        <QueryProvider>
          <RecoilProvider>
            <div className="flex flex-col max-w-md min-h-screen mx-auto">
              <main className="grow pb-[9.5vh]">{children}</main>
              <Footer />
            </div>
          </RecoilProvider>
        </QueryProvider>
      </body>
    </html>
  );
}
