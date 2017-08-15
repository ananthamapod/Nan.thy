var webpack = require('webpack')
var path = require('path')
var AssetsPlugin = require('assets-webpack-plugin')
var ExtractTextPlugin = require('extract-text-webpack-plugin')

var BUILD_DIR = path.resolve(__dirname, 'public')
var APP_DIR = path.resolve(__dirname, 'src')

var config = {
  entry: [
    'babel-polyfill',
    APP_DIR + '/index.js'
  ],
  output: {
    path: BUILD_DIR,
    filename: 'javascripts/bundle.js'
  },
  module: {
    rules: [
      {
        enforce: 'pre',
        test: /\.jsx?$/,
        exclude: /node_modules/,
        loader: 'eslint-loader',
        query: {
          failOnWarning: false,
          failOnError: true
        }
      },
      {
        test: /\.jsx?$/,
        include: APP_DIR,
        loader: 'babel-loader'
      },
      {
        test: /\.scss$/,
        include: [
          APP_DIR,
          path.resolve(__dirname, 'node_modules')
        ],
        loader: ExtractTextPlugin.extract({fallback: 'style-loader', use: 'css-loader!postcss-loader!sass-loader'})
      },
      {
        test: /\.css$/,
        include: '/',
        loader: ExtractTextPlugin.extract({fallback: 'style-loader', use: 'css-loader!postcss-loader'})
      }
    ]
  },
  plugins : [
    /*
    new webpack.optimize.UglifyJsPlugin({
      beautify : false
    }),*/
    new ExtractTextPlugin({
      filename: "stylesheets/[name].css",
      allChunks: true
    }),
    new AssetsPlugin({
      fullPath: true,
      path: path.resolve(__dirname, 'conf'),
      prettyPrint: true}),
    new webpack.ProvidePlugin({
       $: "jquery",
       jQuery: "jquery",
       'window.jQuery': "jquery",
       Popper: ["popper.js", "default"]
    })
  ]
}

module.exports = config