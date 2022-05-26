require patch.inc
LICENSE = "GPLv3"

SRC_URI += "file://0001-Unset-need_charset_alias-when-building-for-musl.patch \
            file://0002-Fix-segfault-with-mangled-rename-patch.patch \
            file://0003-Allow-input-files-to-be-missing-for-ed-style-patches.patch \
            file://0004-Fix-arbitrary-command-execution-in-ed-style-patches-.patch \
            file://0001-Fix-swapping-fake-lines-in-pch_swap.patch \
            file://CVE-2019-13636.patch \
            file://0001-Invoke-ed-directly-instead-of-using-the-shell.patch \
            file://0001-Don-t-leak-temporary-file-on-failed-ed-style-patch.patch \
            file://0001-Don-t-leak-temporary-file-on-failed-multi-file-ed.patch \
            file://CVE-2019-20633.patch \
"

SRC_URI[md5sum] = "78ad9937e4caadcba1526ef1853730d5"
SRC_URI[sha256sum] = "ac610bda97abe0d9f6b7c963255a11dcb196c25e337c61f94e4778d632f1d8fd"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

acpaths = "-I ${S}/m4 "

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'xattr', d)}"
PACKAGECONFIG[xattr] = "--enable-xattr,--disable-xattr,attr,"

PROVIDES_append_class-native = " patch-replacement-native"

BBCLASSEXTEND = "native nativesdk"
