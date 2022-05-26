require iproute2.inc

#FILESPATH_prepend += "${LOCAL_FILES}/iproute:"
#DL_DIR = "${LOCAL_FILES}"
#S = "${WORKDIR}/${BPN}-${PV}"
SRC_URI = "file://iproute2-5.10.0.tar.xz \
           file://0001-libc-compat.h-add-musl-workaround.patch \
           "

SRC_URI[sha256sum] = "bac543435cac208a11db44c9cc8e35aa902befef8750594654ee71941c388f7b"

# CFLAGS are computed in Makefile and reference CCOPTS
#
EXTRA_OEMAKE_append = " CCOPTS='${CFLAGS}'"
