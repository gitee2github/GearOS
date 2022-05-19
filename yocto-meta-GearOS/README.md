**介绍**：

yocto-meta-GerOS核心是构建Yocto Poky之上，但针对openEuler 做了定制化修改，如下为通过Yocto Poky构建GearOS过程；

**开发机注意：**

a、最少 4-6 GB 内存

b、最新版的 Ubuntu 系统（本文使用了 18.04 LTS）

c、磁盘剩余空间至少 60-80 GB

d、在创建 Linux 发行版之前先安装下面的软件包

e、下载最新的 Yocto（Poky 是其最小开发环境）稳定分支

f、在使用yocto时使用普通账号（非root）

**详细步骤**

**1、下载poky（创建一个工作目录，下载poky）**
```
git clone git://git.yoctoproject.org/poky
```
本文用的是yocto 3.3其他分支语法可能有些许差异；
```
poky$ git branch
  master
* yocto-3.3
```
**2、安装相关工具**
```
apt-get install wget git-core unzip make gcc g++ build-essential subversion sed autoconf automake texi2html texinfo coreutils diffstat python-pysqlite2 docbook-utils libsdl1.2-dev libxml-parser-perl libgl1-mesa-dev libglu1-mesa-dev xsltproc desktop-file-utils chrpath groff libtool xterm gawk fop
```
**3、下载完成之后进入 poky目录，然后运行下面的命令为 Yocto 开发环境设置（设置/导出）一些环境变量。**
```
source oe-init-build-env
或者
. ./oe-init-build-env
```
**4、添加openeuler layer**

解压meta-openeuler-21.09.tar.gz之后放到pory文件夹下
```
hy@zhy-virtual-machine:~/GearOS/poky$ ls -l
total 88
drwxrwxr-x  6 GearOS GearOS  4096 11月 29 09:09 bitbake
drwxrwxr-x  7 GearOS GearOS  4096 5月  18 13:45 build
drwxrwxr-x  4 GearOS GearOS  4096 11月 29 09:09 contrib
drwxrwxr-x 18 GearOS GearOS  4096 11月 29 09:09 documentation
-rw-rw-r--  1 GearOS GearOS   834 11月 29 09:09 LICENSE
-rw-rw-r--  1 GearOS GearOS 15394 11月 29 09:09 LICENSE.GPL-2.0-only
-rw-rw-r--  1 GearOS GearOS  1286 11月 29 09:09 LICENSE.MIT
-rw-rw-r--  1 GearOS GearOS  1222 11月 29 09:09 Makefile
-rw-rw-r--  1 GearOS GearOS   244 11月 29 09:09 MEMORIAM
drwxrwxr-x 19 GearOS GearOS  4096 11月 30 16:22 meta
drwxrwxrwx  9 GearOS GearOS  4096 5月  17 17:08 meta-openeuler-21.09
drwxrwxr-x  5 GearOS GearOS  4096 11月 29 09:09 meta-poky
drwxrwxr-x  9 GearOS GearOS  4096 11月 29 09:09 meta-selftest
drwxrwxr-x  8 GearOS GearOS  4096 11月 29 09:09 meta-skeleton
drwxrwxr-x  8 GearOS GearOS  4096 11月 29 09:09 meta-yocto-bsp
-rwxrwxr-x  1 GearOS GearOS  1293 11月 29 09:09 oe-init-build-env
lrwxrwxrwx  1 GearOS GearOS    30 11月 29 09:09 README.hardware -> meta-yocto-bsp/README.hardware
-rw-rw-r--  1 GearOS GearOS   809 11月 29 09:09 README.OE-Core
lrwxrwxrwx  1 GearOS GearOS    21 11月 29 09:09 README.poky -> meta-poky/README.poky
-rw-rw-r--  1 GearOS GearOS   529 11月 29 09:09 README.qemu
drwxrwxr-x  8 GearOS GearOS  4096 11月 29 09:09 scripts
```
修改poky\build\conf\bblayers.conf文件在BBLAYERS中添加openeuler layer
```
BBLAYERS ?= " \
    /GearOS/poky/meta \
    /GearOS/poky/meta-poky \
    /GearOS/poky/meta-yocto-bsp \
    /GearOS/poky/meta-openeuler-21.09 \
```
注：路径合mete路径一样；

**5、修改配置文件 local.conf**
在pory/build
```
MACHINE ??= "qemuarm64"
PREFERRED_PROVIDER_virtual/kernel = "linux-openeuler"
BB_NUMBER_THREADS = '32'
PARALLEL_MAKE = '-j 32'
INHERIT += "extrausers"
EXTRA_USERS_PARAMS += "usermod -P 123456 root;"
hostname_pn-base-files = "GearOS"
```
**6、编译构建GearOS**
```
bitbake core-image-full-cmdline
```
注意：不能使用root用户
