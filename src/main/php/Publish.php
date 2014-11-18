#!/usr/bin/php5
<?php

require(__DIR__ . "/vendor/autoload.php");

define('BASE_URL', realpath(__DIR__ . "/../static"));

$schemaRoot = __DIR__ . "/../resources/schema";
$publishRoot = __DIR__ . "/../resources/api/schema";

$directoryIterator = new RecursiveDirectoryIterator($schemaRoot);
$iterator = new RecursiveIteratorIterator($directoryIterator, RecursiveIteratorIterator::SELF_FIRST);
foreach ($iterator as $file) {
	/**
	 * @var $file SplFileInfo
	 */
	if($file->getExtension() == 'json') {
		$schemaPath = $file->getPath();
		$publishPath = str_replace($schemaRoot, $publishRoot, $schemaPath);
		if(!file_exists($publishPath)) {
			mkdir($publishPath, 0777, true);
			echo "Creating $publishPath\n";
		}
		$parsedContent = parseFile($file);
		file_put_contents($publishPath.'/'.$file->getFilename(), $parsedContent);
	}
}

function parseFile($file) {
	$content = file_get_contents($file);
	$json = json_decode($content, true);
	array_walk_recursive($json, 'replaceToken');
	return json_encode($json, JSON_UNESCAPED_SLASHES);
}

function replaceToken(&$item) {
	if(preg_match('/oe:(\w+\.)+\w+/', $item)) {
		$item = str_replace('.','/', $item);
		$item = str_replace('oe:', BASE_URL . '/', $item) . '.json#';
	}
}