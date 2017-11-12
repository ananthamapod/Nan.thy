# NaN.thy

NaN.thy is a scalable url shortener built on the Akka toolkit with Lightbend's PlayFramework, written mainly in Scala. Because you can't spell scalable without Scala (I know, I know, I'll see myself out :bowtie:). Url shortening as simplicity itself, no signups, just links and publicly available analytics.

## Getting Started

### Prerequisites
* This project requires the Java 8 SDK, install the latest version of [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html) here
* Install either [sbt](http://www.scala-sbt.org/download.html) or [IntelliJ](https://www.jetbrains.com/idea/) to install Scala
* You may need to set your `JAVA_HOME` and `SCALA_HOME` and make sure your sbt installation is added to your PATH
* The frontend code bundling process requires Node.js and npm to be installed, install them [here](https://nodejs.org/en/)

### Installing

1. First move to the directory of your choice and clone the repository

  ```bash
  git clone https://github.com/ananthamapod/Nan.thy.git
  ```

2. Then, once inside the root folder of the project, run

  ```bash
  sbt run
  ```

  to run the server in development mode.

3. For the frontend bundling process, first install the dependencies needed

  ```bash
  npm i
  ```

3. Then, to run the bundling process in development mode, run

  ```bash
  npm run dev
  ```

Et voilà, you should be up and running for easy development and live editing

## Deployment

1. To package the frontend assets for production, run

  ```bash
  npm run prod
  ```

2. Then to package the entire project, run

  ```bash
  sbt dist
  ```

  Now, there should be a zipped file in the `target/universal` folder with all of the jars and other assets needed

3. Unzip the packaged zip file in the target deployment location, and then run the shell script in the `bin/` folder with a production secret, like so:

  ```
  bin/Nan.thy -Dplay.http.secret.key=somethingcrazy
  ```

And assuming our planet doesn't suddenly explode or something, you should be good to go!

## Built With

* [PlayFramework](https://www.playframework.com/) - Highly scalable web framework built on Akka
* [sbt](http://www.scala-sbt.org/) - Simple Build Tool, dependency management for scala, built on Maven and Ivy
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Simply the best Java development environment with a gorgeous dark theme
* [Webpack](https://webpack.github.io/) and [Babel](https://babeljs.io/) - Frontend asset bundling and transpiling for cross browser ES6 compatibility

## Authors

* **Ananth Rao** (@ananthamapod)

## License

MIT

## Inspiration
Well, not that a URL shortener is really some innovative concept that needs special inspiration, but this [Quora question](https://www.quora.com/What-is-the-architecture-of-a-scalable-URL-shortener) gave me the initial push to make this project. It occurred to me that despite having been doing web development for a while, I'd never even built the Holy Grail of starter web projects before! And that's all I needed anyway, I just needed a reason to play around with the Play framework, I mean, it's in the name! (Alright, I'll stop :neckbeard:).
