/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hopkins.collections;

/**
 * @author ian_000
 */
interface MapEntryIteratorFactory<K, V> {
  Iterator<Map.Entry<K, V>> newIterator();
}
