var StyleLintPlugin = require('stylelint-webpack-plugin')

var config = {
  module: {
    rules: [
      {
        enforce: 'pre',
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'eslint-loader',
        options: {
          emitWarning: true
        }
      }
    ]
  },
  plugins : [
    new StyleLintPlugin()
  ]
}

module.exports = config