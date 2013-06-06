#!/bin/bash
dir="$(dirname "${BASH_SOURCE[0]}")"
cd "$dir/.."

css='target/css'
components='target/components'
less='node_modules/less/bin/lessc'

mkdir -p $css

$less less/bootstrap.less > $css/bootstrap.css
$less --yui-compress less/bootstrap.less > $css/bootstrap.min.css
$less $components/bootstrap/less/responsive.less > $css/responsive.css
$less --yui-compress $components/bootstrap/less/responsive.less > $css/responsive.min.css
cp $components/font-awesome/font/ target -R
