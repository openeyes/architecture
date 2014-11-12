#!/usr/bin/php5
<?php

require(__DIR__ . "/vendor/autoload.php");

$schemaStore = new SchemaStore();

foreach(glob(__DIR__ . "/../resources/openeyes/schema/*.json") as $schemaPath) {
        $schemaStore->add("oe:" . str_replace(".json", "", basename($schemaPath)), json_decode(file_get_contents($schemaPath)));
}

$testVal = (object)array(
    "numerator" => 6,
    "denominator" => 6,
);

var_dump(Jsv4::validate($testVal, $schemaStore->get("oe:VisualAcuityValueSnellenMetre")));
