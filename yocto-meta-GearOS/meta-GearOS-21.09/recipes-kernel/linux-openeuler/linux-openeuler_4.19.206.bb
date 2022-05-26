DESCRIPTION = "Openeuler Kernel of v4.19.206 && xenomai"
LICENSE = "CLOSED"

inherit kernel

SRC_URI = "git://gitee.com/openeuler/kernel.git;protocol=https;branch=kernel-4.19 \
	file://defconfig \
	file://0001-linux-4.19.206.oe1_FT_ethernet.patch \
	file://0002-ipipe-core-4.19.55-oe1.patch \
	file://0003-enable_irq.patch \
	file://0004-cobalt-core-3.1-4.19.206.patch \
	file://0005-cobalt-core-3.1-4.19.90-oe1.patch \
	file://0006-hwcap.patch \
	  "
S = "${WORKDIR}/git"

LINUX_VERSION ?= "4.19.206"
LINUX_VERSION_EXTENSION_append = "-2012.4.0.0053.oe1-xeno"

KERNEL_FEATURES_remove= " features/debug/printk.scc"
KERNEL_FEATURES_remove= " features/kernel-sample/kernel-sample.scc"

SRCREV="c3a5f86eb282feda8686d3e47c1b3bc16d04b8d5"

COMPATIBLE_MACHINE = "${MACHINE}"
