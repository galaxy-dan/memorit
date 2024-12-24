/** @type {import('next').NextConfig} */
const nextConfig = {
  env: {
    // NEXT_PUBLIC_BACKEND_URL: process.env.NEXT_PUBLIC_BACKEND_URL,
    NEXT_PUBLIC_BACKEND_URL: 'http://localhost:9090',
  },
  images: {
    domains: ['mermorit.s3.ap-northeast-2.amazonaws.com'],
  },
};

module.exports = nextConfig;
