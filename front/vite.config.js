import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite';
import { ArcoResolver } from 'unplugin-vue-components/resolvers';

// https://vitejs.dev/config/
export default defineConfig({
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8090/',// 后端接口
                changeOrigin: true, // 是否跨域
                pathRewrite: {
                    '/api': ''
                }
            }
        }
    },
  plugins: [
      vue(),
      AutoImport({
        resolvers: [ArcoResolver()],
      }),
      Components({
        resolvers: [
          ArcoResolver({
            sideEffect: true
          })
        ]
      })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
    define:{
      'process.env':{
          BASE_URL:"http://localhost:8090",
          BASE_MEDIA: "http://localhost:8090/media/",
      }
    }
})
