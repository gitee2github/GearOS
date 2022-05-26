#!/bin/bash
function dir_list(){
	# 对文件夹进行遍历
	for file1 in $1
		do
			# 判断是否是文件夹
			if [ -d "$file1" ] 
			then
				#echo "$file1  $file &&&&&&&&&is director"
				#echo "${file1##*/}      ${file#*/} "
				#if [[["${file1##*/}"]==["${file#*/}"]]]
				#if [ $str = denglin ]
				if [ ${file1##*/} = ${file#*/} ]
				then
				echo "$file1  $file "
				cp  $file/*.tar* $file1/*/
				break
				fi
				#dir_list $file"/*"
			# 判断是否是文件
			elif [ -f "$file" ] 
			then
				echo "$file is file"
			fi
		done
}

for file in ./*
		do
			# 判断是否是文件夹
			if [ -d "$file" ] 
			then
				echo "$file is director"
				# 如果是文件夹 递归调用
				dir_list "../recipes*/*"
			# 判断是否是文件
			elif [ -f "$file" ] 
			then
				echo "$file is file"
			fi
done
cp ./libxml2/libxml2-2.9.10.tar.gz ../recipes-core/libxml/libxml2/libxml2-2.9.10.tar.gz
cp ./perl-libxml-perl/libxml-perl-0.08.tar.gz ../recipes-devtools/perl/files/libxml-perl-0.08.tar.gz
cp ./perl/perl-5.32.0.tar.xz ../recipes-devtools/perl/files/perl-5.32.0.tar.xz
cp ./gzip/gzip-1.10.tar.xz ../recipes-extended/gzip/files/gzip-1.10.tar.xz
cp ./libidn2/libidn2-2.3.0.tar.gz ../recipes-extended/libidn/libidn2/libidn2-2.3.0.tar.gz
cp ./libtasn1/libtasn1-4.16.0.tar.gz  ../recipes-support/gnutls/libtasn1/libtasn1-4.16.0.tar.gz
cp ./iproute/iproute2-5.10.0.tar.xz ../recipes-connectivity/iproute2/iproute2/iproute2-5.10.0.tar.xz

