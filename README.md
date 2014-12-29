mterm
=====

A simple java terminal emulator running on top of [aesh](https://github.com/aeshell/aesh).


![Alt mterm](https://raw.githubusercontent.com/aeshell/mterm/master/mterm.png)


Download:
---------

[mterm-0.56.1.zip](https://github.com/aeshell/mterm/releases/download/0.56.1/mterm-0.56.1.zip)


```shell
$ unzip mterm-0.56.1.zip 
```

```shell
$ java -jar mterm-0.56.1.jar 
```

Your own build:
---------------

```shell
$ git clone https://github.com/aeshell/aesh.git
```

```shell
$ cd aesh 
```

```shell
$ ./gradlew install 
```

```shell
$ git clone https://github.com/aeshell/aesh-extensions.git
```

```shell
$ cd aesh-extensions 
```

```shell
$ ./gradlew install 
```

```shell
$ git clone https://github.com/aeshell/mterm.git 
```

```shell
$ cd mterm 
```

```shell
$ ./gradlew build
```

```shell
$ ./gradlew run
```
