const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // publicPath: '/admin/',
  // configureWebpack: {
  //   entry: "./src/main.js",
  //   devServer: {
  //     hot: true,
  //     allowedHosts: "all"
  //   },
  //   watch: true,
  //   watchOptions: {
  //     ignored: /node_modules/,
  //     poll: 1000,
  //   },
  // },
  devServer: {
    hot: true,
    allowedHosts: "all",
    compress: true,
    disableHostCheck: true,
  },
})
